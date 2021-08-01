package com.harismexis.hiltproject.base

import com.harismexis.hiltproject.parser.MockHerosProvider
import com.harismexis.hiltproject.util.InstrumentedFileReader

open class BaseInstrumentedTest {
    protected val fileParser = InstrumentedFileReader()
    protected val herosParser = MockHerosProvider(fileParser)
}