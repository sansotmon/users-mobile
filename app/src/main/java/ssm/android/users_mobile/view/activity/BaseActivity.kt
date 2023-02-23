package ssm.android.users_mobile.view.activity

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ssm.android.users_mobile.R
import ssm.android.users_mobile.presenter.BaseUI

open class BaseActivity: AppCompatActivity(), BaseUI {

    private var mAlertDialog: AlertDialog? = null
    private var mAlertAskDialog: AlertDialog? = null
    private var mProgressDialog: AlertDialog? = null

    override fun showMessageDialog(message: String) {
        runOnUiThread {

            if (mAlertDialog != null) {
                mAlertDialog?.setMessage(message)
                mAlertDialog?.show()
            } else {
                mAlertDialog = AlertDialog.Builder(this)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }.show()

                mAlertDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(
                    resources.getColor(
                        R.color.colorPrimaryDark
                    )
                )
            }

        }
    }

    override fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun hideMessageDialog() {
        mAlertDialog?.dismiss()
        mAlertAskDialog?.dismiss()
    }

    override fun showProgressDialog(resourceMessage: Int) {
        showProgressDialog(resources.getString(resourceMessage))
    }

    override fun showProgressDialog(message: String){
        hideMessageDialog()
        runOnUiThread {
            if (mProgressDialog != null) {
                mProgressDialog?.findViewById<TextView>(R.id.loading_msg)!!.text = message
                mProgressDialog?.show()
            } else {
                mProgressDialog = AlertDialog.Builder(this)
                    .setView(R.layout.progress_view)
                    .setCancelable(false)
                    .show()

                mProgressDialog?.findViewById<TextView>(R.id.loading_msg)!!.text = message
            }
        }
    }

    override fun hideProgressDialog(){
        mProgressDialog?.dismiss()
    }
}