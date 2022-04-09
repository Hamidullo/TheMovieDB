package com.example.movie.utils

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import android.widget.TextView.BufferType


private fun addClickablePartTextViewResizable(
    strSpanned: Spanned, tv: TextView,
    maxLine: Int, spanableText: String, viewMore: Boolean
): SpannableStringBuilder? {
    val str = strSpanned.toString()
    val ssb = SpannableStringBuilder(strSpanned)
    if (str.contains(spanableText)) {
        ssb.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                tv.layoutParams = tv.layoutParams
                tv.setText(tv.tag.toString(), BufferType.SPANNABLE)
                tv.invalidate()
                if (viewMore) {
                    makeTextViewResizable(tv, -1, "View Less", false)
                } else {
                    makeTextViewResizable(tv, 3, "View More", true)
                }
            }
        }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length, 0)
    }
    return ssb
}

fun makeTextViewResizable(tv: TextView, maxLine: Int, expandText: String, viewMore: Boolean) {
    if (tv.tag == null) {
        tv.tag = tv.text
    }
    val vto = tv.viewTreeObserver
    vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            val text: String
            val lineEndIndex: Int
            val obs = tv.viewTreeObserver
            obs.removeOnGlobalLayoutListener(this)
            if (maxLine == 0) {
                lineEndIndex = tv.layout.getLineEnd(0)
                text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                    .toString() + " " + expandText
            } else if (maxLine > 0 && tv.lineCount >= maxLine) {
                lineEndIndex = tv.layout.getLineEnd(maxLine - 1)
                text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                    .toString() + " " + expandText
            } else {
                lineEndIndex = tv.layout.getLineEnd(tv.layout.lineCount - 1)
                text = tv.text.subSequence(0, lineEndIndex).toString() + " " + expandText
            }
            tv.text = text
            tv.movementMethod = LinkMovementMethod.getInstance()
            tv.setText(
                addClickablePartTextViewResizable(
                    SpannableString(tv.text.toString()), tv, lineEndIndex, expandText,
                    viewMore
                ), BufferType.SPANNABLE
            )
        }
    })
}