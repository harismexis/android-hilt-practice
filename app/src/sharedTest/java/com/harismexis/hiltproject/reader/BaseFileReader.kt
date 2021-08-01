package com.harismexis.hiltproject.reader

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String

}