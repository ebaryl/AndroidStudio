import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newproject.ContactsListViewState
import com.example.newproject.MainActivity
import com.example.newproject.generateFakeContactsList

class ContactsListViewModel : ViewModel() {

	var viewState by mutableStateOf(ContactsListViewState())
		private set

	init {
		viewState = ContactsListViewState(
			list = generateFakeContactsList(count = 100)
		)
	}
}
