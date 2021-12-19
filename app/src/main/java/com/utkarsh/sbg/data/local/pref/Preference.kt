package com.utkarsh.sbg.data.local.pref

interface Preference {
    fun setBookMarkChapter(position: Int)
    fun getBookMarkChapter(): Int? = null
    fun setBookMarkVerse(position: Int)
    fun getBookMarkVerse(): Int? = null
    fun clear()
}
