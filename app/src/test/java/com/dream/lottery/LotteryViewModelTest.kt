package com.dream.lottery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dream.lottery.TestData.lotteryDraw
import com.dream.lottery.TestData.lotteryDrawsList
import com.dream.lottery.data.LotteryDraw
import com.dream.lottery.data.LotteryRepository
import com.dream.lottery.ui.LotteryViewModel
import com.dream.lottery.utils.DataResponse
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*


@ExperimentalCoroutinesApi
class LotteryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: LotteryViewModel
    private lateinit var repository: LotteryRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        viewModel = LotteryViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchLotteryDraws emits success when repository returns success`() =
        testScope.runBlockingTest {

            val response = flowOf(DataResponse.Success(lotteryDrawsList))

            coEvery { repository.getLotteryList() } returns response

            viewModel.fetchLotteryDraws()

            val state = viewModel.lotteryDraws.first()
            assertTrue(state is DataResponse.Success)
            assertEquals(lotteryDrawsList, (state as DataResponse.Success).data)
        }


    @Test
    fun `generateRandomTickets generates correct number of tickets`() {
        val tickets = viewModel.generateRandomTickets(lotteryDraw, 5)

        assertEquals(5, tickets.size)
    }

    @Test
    fun `generateRandomTickets generates valid tickets`() {
        val tickets = viewModel.generateRandomTickets(lotteryDraw, 5)

        tickets.forEach { ticket ->
            assertEquals(6, ticket.numbers.size)
            ticket.numbers.forEach { number ->
                assertTrue(number in 1..60)
            }
        }
    }


    @Test
    fun `fetchLotteryDraws emits error when repository returns error`() =
        testScope.runBlockingTest {
            val response = flowOf<DataResponse<List<LotteryDraw>>>(DataResponse.Error("Error"))

            coEvery { repository.getLotteryList() } returns response

            viewModel.fetchLotteryDraws()

            val state = viewModel.lotteryDraws.first()
            assertTrue(state is DataResponse.Error)
        }

}
