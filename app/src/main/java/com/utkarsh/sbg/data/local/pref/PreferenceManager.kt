package com.utkarsh.sbg.data.local.pref

import com.farmit.data.local.pref.EncPref

class PreferenceManager(private val sharedPreferences: EncPref) : Preference {

    companion object {
        private const val BOOKMARK_VERSE = "BOOKMARK_VERSE"
        private const val BOOKMARK_CHAPTER = "BOOKMARK_CHAPTER"
    }

    override fun setBookMarkVerse(position: Int) {
        sharedPreferences.putInt(BOOKMARK_VERSE, position)
    }

    override fun getBookMarkVerse(): Int {
        return sharedPreferences.getInt(BOOKMARK_VERSE)
    }

    override fun clear() {
        sharedPreferences.clear()
    }

    override fun setBookMarkChapter(position: Int) {
        sharedPreferences.putInt(BOOKMARK_CHAPTER, position)
    }

    override fun getBookMarkChapter(): Int {
        return sharedPreferences.getInt(BOOKMARK_CHAPTER)
    }

}
