package com.harismexis.hiltproject.parser

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String

}