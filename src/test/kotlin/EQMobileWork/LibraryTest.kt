package EQMobileWork

import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertTrue

@ExperimentalSerializationApi
class LibraryTest {
    @Test
    fun testSetup() {
        val classUnderTest = Library()
        assertTrue(classUnderTest.setup("https://httpbin.org/post/"), "setup should return 'true'")

    }

    @Test
    fun testLog() {
        val classUnderTest = Library()

        // init lib for log test
        classUnderTest.setup("https://httpbin.org/post/")
        classUnderTest.log(LocationEvent(0F, 0F, System.currentTimeMillis() / 1000L, "empty"))
//        assertTrue(classUnderTest.isSuccessful(),"The response from Retrofit post call should be true ")
    }
}
