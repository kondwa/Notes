package co.milesoft.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        signInMessage = "Sign-in to see settings"

        val authUI = AuthUI.getInstance()
        btnSignOut.setOnClickListener {
            authUI.signOut(this)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this,MainActivity::class.java))
                    }else{
                        Log.e(TAG,"Sign-out failed",task.exception)
                        Toast.makeText(this,"Sign-out failed.",Toast.LENGTH_SHORT)
                    }
                }
        }
        btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(this,R.style.DialogueTheme)
                .setTitle("Delete Account")
                .setMessage("This is permanent, are you sure?")
                .setPositiveButton("Yes"){_,_->
                    authUI.delete(this)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful){
                                startActivity(Intent(this,MainActivity::class.java))
                            }else{
                                Log.e(TAG,"Delete account failed",task.exception)
                                Toast.makeText(this,"Delete account failed",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                .setNegativeButton("No",null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        etName.setText(user?.displayName)
    }

    override fun onPause() {
        super.onPause()
        val profile = UserProfileChangeRequest.Builder()
            .setDisplayName(etName.text.toString())
            .build()
        user?.let {
            user?.updateProfile(profile)
                ?.addOnCompleteListener { task->
                    if(!task.isSuccessful){
                        Log.e(TAG,"Profile update failed.")
                        Toast.makeText(this,"Profile update failed.",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    companion object{
        private val TAG = SettingsActivity::class.qualifiedName
    }
}