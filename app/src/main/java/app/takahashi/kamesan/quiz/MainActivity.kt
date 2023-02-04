package app.takahashi.kamesan.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.takahashi.kamesan.quiz.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}

        val quizIntent: Intent = Intent(this, QuizActivity::class.java)

        binding.startButton.setOnClickListener{
            startActivity(quizIntent)
        }

    }
}