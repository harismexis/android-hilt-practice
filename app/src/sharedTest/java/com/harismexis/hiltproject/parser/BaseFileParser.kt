package com.harismexis.hiltproject.parser

abstract class BaseFileParser {

    abstract fun getFileAsString(filePath: String): String

}