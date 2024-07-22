package com.dream.lottery

import com.dream.lottery.TestData.lotteryDrawsList
import com.dream.lottery.data.LotteryApiService
import com.dream.lottery.data.LotteryDrawDao
import com.dream.lottery.data.LotteryList
import com.dream.lottery.data.LotteryRepository
import com.dream.lottery.utils.DataResponse
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class LotteryRepositoryTest {

    private lateinit var lotteryDrawDao: LotteryDrawDao
    private lateinit var remoteDataSource: LotteryApiService
    private lateinit var repository: LotteryRepository

    @Before
    fun setUp() {
        lotteryDrawDao = mockk(relaxed = true)
        remoteDataSource = mockk(relaxed = true)
        repository = LotteryRepository(lotteryDrawDao, remoteDataSource)
    }

    @Test
    fun `getLotteryList emits success when API call is successful`() = runBlockingTest {

        val mockResponse = LotteryList(lotteryDrawsList)

        coEvery { remoteDataSource.getLotteryList() } returns Response.success(mockResponse)
        coEvery { lotteryDrawDao.getAllDraws() } returns emptyList()
        coEvery { lotteryDrawDao.insertDraws(lotteryDrawsList) } just Runs

        val result = repository.getLotteryList().last()

        coVerify { lotteryDrawDao.insertDraws(mockResponse.draws) }
        assertTrue(result is DataResponse.Success && result.data == mockResponse.draws)
    }

    @Test
    fun `getLotteryList emits error when API call fails and local cache is empty`() =
        runBlockingTest {
            coEvery { remoteDataSource.getLotteryList() } returns Response.error(
                500,
                ResponseBody.create(null, "Internal server error")
            )
            coEvery { lotteryDrawDao.getAllDraws() } returns emptyList()

            val result = repository.getLotteryList().last()

            assertTrue(result is DataResponse.Error)
        }

    @Test
    fun `getLotteryList emits success from local cache when API call fails`() = runBlockingTest {
        val localCache = lotteryDrawsList

        coEvery { remoteDataSource.getLotteryList() } returns Response.error(
            500,
            ResponseBody.create(null, "Internal server error")
        )
        coEvery { lotteryDrawDao.getAllDraws() } returns localCache

        val result = repository.getLotteryList().last()

        assertTrue(result is DataResponse.Success && result.data == localCache)
    }

    @Test
    fun `getLotteryList emits error when API call body is null`() = runBlockingTest {
        coEvery { remoteDataSource.getLotteryList() } returns Response.success(null)
        coEvery { lotteryDrawDao.getAllDraws() } returns emptyList()

        val result = repository.getLotteryList().last()

        assertTrue(result is DataResponse.Error)
    }

    @Test
    fun `getLotteryList emits error when API call body is empty`() = runBlockingTest {
        val mockResponse = LotteryList(emptyList())

        coEvery { remoteDataSource.getLotteryList() } returns Response.success(mockResponse)
        coEvery { lotteryDrawDao.getAllDraws() } returns emptyList()

        val result = repository.getLotteryList().last()

        assertTrue(result is DataResponse.Error)
    }

    @Test
    fun `getLotteryList emits error when API call throws exception`() = runBlockingTest {
        coEvery { remoteDataSource.getLotteryList() } throws RuntimeException("Network error")
        coEvery { lotteryDrawDao.getAllDraws() } returns emptyList()

        val result = repository.getLotteryList().last()

        assertTrue(result is DataResponse.Error)
    }
}
