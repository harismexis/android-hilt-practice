package com.harismexis.hiltproject.setup.testutil

import androidx.test.platform.app.InstrumentationRegistry
import com.harismexis.hiltproject.parser.BaseFileParser

class InstrumentedFileParser: BaseFileParser() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

