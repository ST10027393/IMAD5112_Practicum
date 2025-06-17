package vcmsa.projects.st10027393_imad5112_practicum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import vcmsa.projects.st10027393_imad5112_practicum.R
import vcmsa.projects.st10027393_imad5112_practicum.SecondActivity

class MainActivity : AppCompatActivity() {

    // Parallel arrays
        val itemNames = ArrayList<String>()
        val categories = ArrayList<String>()
        val quantities = ArrayList<Int>()
        val comments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addItemButton = findViewById<Button>(R.id.packList)
        val viewListButton = findViewById<Button>(R.id.screenTwo)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addItemButton.setOnClickListener {
            showAddDialog()
        }

        viewListButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putStringArrayListExtra("items", itemNames)
            intent.putStringArrayListExtra("categories", categories)
            intent.putIntegerArrayListExtra("quantities", ArrayList(quantities))
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }

    private fun showAddDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Item Details")
        val layout = layoutInflater.inflate(R.layout.dialog_layout, null)
        builder.setView(layout)

        val itemName = layout.findViewById<android.widget.EditText>(R.id.itemName)
        val category = layout.findViewById<android.widget.EditText>(R.id.category)
        val quantity = layout.findViewById<android.widget.EditText>(R.id.quantity)
        val comment = layout.findViewById<android.widget.EditText>(R.id.comment)

        builder.setPositiveButton("Add") { _, _ ->
            val name = itemName.text.toString()
            val cat = category.text.toString()
            val qty = quantity.text.toString().toIntOrNull()
            val com = comment.text.toString()

            if (name.isEmpty() || cat.isEmpty() || qty == null) {
                Toast.makeText(this, "Please enter valid item details.", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            itemNames.add(name)
            categories.add(cat)
            quantities.add(qty)
            comments.add(com)

            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
}
