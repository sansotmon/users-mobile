package ssm.android.users_mobile.contract

import android.provider.BaseColumns

object UserContract {

    object UserEntry : BaseColumns {
        const val TABLE_NAME = "users"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_PHONE = "phone"
    }
}