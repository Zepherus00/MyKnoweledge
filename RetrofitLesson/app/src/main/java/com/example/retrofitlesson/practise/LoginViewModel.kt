package com.example.retrofitlesson.practise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val token = MutableLiveData<String>()
}