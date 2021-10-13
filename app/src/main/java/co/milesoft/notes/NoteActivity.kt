package co.milesoft.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        val user = FirebaseAuth.getInstance().currentUser
        if (user==null || user.isAnonymous){
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(SIGNIN_MESSAGE,"Sign-in to create a new note")
            startActivityForResult(intent, ATTEMPT_SIGNIN)
        }

        btnCancel.setOnClickListener { finish() }
        btnSave.setOnClickListener {
            Toast.makeText(this,"Note saved.",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ATTEMPT_SIGNIN && resultCode == Activity.RESULT_CANCELED){
            finish()
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnuSignOut ->{
                FirebaseAuth.getInstance().signOut()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object{
        const val SIGNIN_MESSAGE = "signin_message"
        const val ATTEMPT_SIGNIN = 10
    }
}