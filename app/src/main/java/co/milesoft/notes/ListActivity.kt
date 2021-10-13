package co.milesoft.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        floatingActionButton.setOnClickListener {
            //val user = FirebaseAuth.getInstance().currentUser
            //if(user!=null){
            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
            //}else{
              //  finish()
            //}
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
}