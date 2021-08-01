package com.harismexis.hiltproject.setup.testutil

import androidx.test.platform.app.InstrumentationRegistry
import com.harismexis.hiltproject.parser.BaseFileReader

class InstrumentedFileReader: BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}
