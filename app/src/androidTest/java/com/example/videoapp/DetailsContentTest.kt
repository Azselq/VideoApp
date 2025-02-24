import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.presentation.details.DetailsComponent
import com.example.videoapp.presentation.details.DetailsContent
import com.example.videoapp.presentation.details.DetailsStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test


class DetailsContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private class FakeDetailsComponent(
        initialState: DetailsStore.State,
        private val onBackClickedCallback: () -> Unit = {},
        private val onToggleFullScreenCallback: () -> Unit = {},
        private val onPlaybackErrorCallback: () -> Unit = {}
    ) : DetailsComponent {
        private val _stateFlow = MutableStateFlow(initialState)
        override val model: StateFlow<DetailsStore.State> = _stateFlow

        override fun onClickBack() {
            onBackClickedCallback()
        }

        override fun onFullScreenToggle() {
            onToggleFullScreenCallback()
        }

        override fun onPlaybackError(errorMessage: String) {
            onPlaybackErrorCallback()
        }

        fun updateState(newState: DetailsStore.State) {
            _stateFlow.value = newState
        }
    }

    @Test
    fun loadedState_DisplaysVideoPlayer() {
        val loadedState = DetailsStore.State(
            detailsState = DetailsStore.State.DetailsState.Loaded,
            video = Video(
                url = "https://example.com/dummy.mp4",
                id = 2,
                tags = "tag1, tag2",
                duration = 120,
                thumbnail = "image2"
            )
        )

        val fakeComponent = FakeDetailsComponent(initialState = loadedState)

        composeTestRule.setContent {
            DetailsContent(component = fakeComponent)
        }

        composeTestRule.onNode(hasContentDescription("Full screen"))
            .assertExists()
    }
}
