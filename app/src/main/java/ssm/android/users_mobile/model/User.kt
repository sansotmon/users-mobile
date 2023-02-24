package ssm.android.users_mobile.model

import com.google.gson.annotations.SerializedName

class User(
    var id: String,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var website: String? = null,
    var address: Address? = null,
    var company: Company? = null
) {
    class Users {
        @SerializedName("id")
        var users: ArrayList<User>? = null
    }
}