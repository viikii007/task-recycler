# TaskRecycler Project

This project is an Android application built using Kotlin, XML, and the MVVM architecture. It demonstrates the following features:

- **SQLite Database & Pagination:**  
  Inserts 1000 dummy records into an SQLite database and displays them in a RecyclerView with pagination (20 items per scroll).

- **Navigation:**  
  Clicking on any item navigates to a detail screen (second activity) that shows the item name along with a top bar (Toolbar) with a back button.

- **API Integration in Fragment:**  
  The detail screen hosts a fragment that contains a button to trigger a Retrofit GET API call. A public API ([JSONPlaceholder](https://jsonplaceholder.typicode.com/todos/1)) is used as an example. While loading, a progress bar is displayed, and the response data is shown when the API call completes.

## Project Structure

- **MyDatabaseHelper.kt:**  
  Creates the SQLite database, table, and inserts dummy data (only once).

- **ItemRepository.kt:**  
  Provides data fetching functionality from the SQLite database with proper limit and offset for pagination.

- **ItemViewModel.kt & ViewModelFactory.kt:**  
  Implements the MVVM architecture for paginated data retrieval and exposes data to the UI.

- **ItemAdapter.kt:**  
  A RecyclerView adapter that displays each item and handles item click events to navigate to the detail screen.

- **MainActivity.kt:**  
  Hosts the RecyclerView, sets up pagination, and displays a top bar with a title.

- **ItemDetailActivity.kt:**  
  Displays the selected item's details with a Toolbar that includes a back button. It also hosts a fragment for API integration.

- **ApiFragment.kt, ApiViewModel.kt, ApiRepository.kt, ApiService.kt:**  
  Implements API integration using Retrofit with MVVM. The fragment contains a button to fetch API data, shows a progress loader, and displays the API response.


### How to Run

1. **Clone the Repository:**
   - Use Android Studio's VCS integration or Git command line to clone this repository.

2. **Open the Project:**
   - Open the project in Android Studio.

3. **Build and Run:**
   - Click the "Run" button to build and deploy the app on your emulator/device.

## Technologies Used

- **Kotlin**
- **Android SDK**
- **SQLite Database**
- **RecyclerView & MVVM Architecture**
- **Retrofit** for API integration
- **AndroidX Libraries**

## Acknowledgements
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/) for the public API.

