package co.milesoft.notes

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

open class BaseActivity:AppCompatActivity() {
    protected var userId = "-1"
        private set
    protected var user:FirebaseUser? = null
        private set
    protected var signInMessage = "Sign-in to complete this action"

    override fun onResume() {
        super.onResume()
        user = FirebaseAuth.getInstance().currentUser
        if (user==null || user?.isAnonymous != false || user?.providerData?.size == 0){
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(MainActivity.SIGNIN_MESSAGE,signInMessage)
            startActivityForResult(intent,ATTEMPT_SIGN_IN)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ATTEMPT_SIGN_IN && resultCode == Activity.RESULT_CANCELED){
            finish()
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object{
        const val ATTEMPT_SIGN_IN = 10
    }
}