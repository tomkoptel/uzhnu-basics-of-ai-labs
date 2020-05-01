package uzhnu.edu.bai.dialog.ktor

import uzhnu.edu.bai.dialog.response.BreedResult

interface KtorApi {
    suspend fun getJsonFromApi(): BreedResult
}
