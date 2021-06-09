package com.harismexis.hiltproject.setup.base

import com.harismexis.hiltproject.parser.MockHerosParser
import com.harismexis.hiltproject.setup.testutil.InstrumentedFileParser

open class InstrumentedTestSetup {
    protected val fileParser = InstrumentedFileParser()
    protected val herosParser = MockHerosParser(fileParser)
}