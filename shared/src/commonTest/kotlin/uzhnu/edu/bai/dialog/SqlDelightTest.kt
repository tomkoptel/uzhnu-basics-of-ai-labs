package uzhnu.edu.bai.dialog

import com.squareup.sqldelight.db.SqlDriver
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal expect fun testDbConnection(): SqlDriver

internal expect fun <T> runTest(block: suspend () -> T)

abstract class SqlDelightTest {

    private lateinit var dbHelper: DatabaseHelper

    private suspend fun DatabaseHelper.insertBreed(name: String) {
        insertBreeds(listOf(name))
    }

    @BeforeTest
    fun setup() = runTest {
        dbHelper = DatabaseHelper(testDbConnection())
        dbHelper.deleteAll()
        dbHelper.insertBreed("Beagle")
    }

    @Test
    fun Select_All_Items_Success() = runTest {
        val breeds = dbHelper.selectAllItems().executeAsList()
        assertNotNull(
            breeds.find { it.name == "Beagle"},
            "Could not retrieve Breed"
        )
    }

    @Test
    fun Select_Item_by_Id_Success() = runTest {
        val breeds = dbHelper.selectAllItems().executeAsList()
        val firstBreed = breeds.first()
        assertNotNull(dbHelper.selectById(firstBreed.id).executeAsOneOrNull(),
            "Could not retrieve Breed by Id")
    }

    @Test
    fun Update_Favorite_Success() = runTest {
        val breeds = dbHelper.selectAllItems().executeAsList()
        val firstBreed = breeds.first()
        dbHelper.updateFavorite(firstBreed.id,true)
        val newBreed = dbHelper.selectById(firstBreed.id).executeAsOneOrNull()
        assertNotNull(
            newBreed,
            "Could not retrieve Breed by Id"
        )
        assertTrue(
            newBreed.isFavorited(),
            "Favorite Did Not Save"
        )
    }

    @Test
    fun Delete_All_Success() = runTest {
        dbHelper.insertBreed("Poodle")
        dbHelper.insertBreed("Schnauzer")
        assertTrue(dbHelper.selectAllItems().executeAsList().isNotEmpty())
        dbHelper.deleteAll()
        assertTrue(
            dbHelper.selectAllItems().executeAsList().count() == 0,
            "Delete All did not work"
        )
    }
}
