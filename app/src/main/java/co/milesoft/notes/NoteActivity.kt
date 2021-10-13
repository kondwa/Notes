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

class NoteActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        signInMessage = "Sign-in to create a new note."
        btnCancel.setOnClickListener { finish() }
        btnSave.setOnClickListener {
            Toast.makeText(this,"Note saved.",Toast.LENGTH_SHORT).show()
        }
    }

}