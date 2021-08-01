package com.harismexis.hiltproject.base

import com.harismexis.hiltproject.reader.MockHerosProvider
import com.harismexis.hiltproject.util.InstrumentedFileReader

open class BaseInstrumentedTest {
    protected val fileParser = InstrumentedFileReader()
    protected val herosParser = MockHerosProvider(fileParser)
}