package dev.forcetower.heroes.view.expensive

import android.widget.TextView
import androidx.databinding.BindingAdapter
import dev.forcetower.heroes.R

@BindingAdapter(value = ["comicTitle", "comicIssueNumber"], requireAll = true)
fun formatComicTitle(tv: TextView, title: String?, number: Int?) {
    if (title == null) {
        tv.text = ""
    } else {
        tv.text = tv.context.getString(R.string.comic_title_format, number ?: 0, title)
    }
}