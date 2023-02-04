package app.takahashi.kamesan.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.takahashi.kamesan.quiz.databinding.ActivityQuizBinding
import android.util.Log
import androidx.core.view.isVisible

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    val quizlists: List<List<String>> = listOf(
        listOf("プログラマーの上谷隆宏によ/","chatGPT","Google","twitter","pixiv"),
        listOf("1951年に『真説石川五右衛門』『長/","檀一雄","池井戸潤","井伏鱒二","川口松太郎"),
        listOf("OpenAIによって2022年11月に/","ChatGPT","Siri","GoogleHome","Switchbot")
    )

    val shuffledLists: List<List<String>> = quizlists.shuffled()

    //クイズ数をカウントする変数を作る
    var quizCount:Int = 0

    //　正解の回数を入れる変数を作る
    var correctCount: Int = 0

    // 正解の答えを入れる変数を作る
    var correctAnswer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater).apply{setContentView(this.root)}

        // クイズを表示
        showQuestion()

        binding.answerButton1.setOnClickListener {
            checkAnswer(binding.answerButton1.text.toString())
        }
        binding.answerButton2.setOnClickListener {
            checkAnswer(binding.answerButton2.text.toString())
        }
        binding.answerButton3.setOnClickListener {
            checkAnswer(binding.answerButton3.text.toString())
        }
        binding.nextButton.setOnClickListener {
            if(quizCount == quizlists.size){
                val resultIntent: Intent = Intent(this,ResultActivity::class.java)
                resultIntent.putExtra("QuizCount", quizlists.size)
                resultIntent.putExtra("CorrectCount", correctCount)
                startActivity(resultIntent)
            }else{
                // 判定画像を非表示にする
                binding.judgeImage.isVisible = false
                binding.nextButton.isVisible = false
                // 回数ボタンを有効にする
                binding.answerButton1.isEnabled = true
                binding.answerButton2.isEnabled = true
                binding.answerButton3.isEnabled = true
                // 正解表示をリセットする
                binding.correctAnswerText.text = ""
                // クイズを表示する
                showQuestion()
            }
        }
    }

    fun showQuestion(){
        val question: List<String> = shuffledLists[quizCount]

        Log.d("debug",shuffledLists[0][0].toString())

        // クイズを、TextViewに反映する
        binding.quizText.text = question[0]

        // クイズの選択肢を表示する
        binding.answerButton1.text = question[1]
        binding.answerButton2.text = question[2]
        binding.answerButton3.text = question[3]

        // クイズの正しい答えをセットする
        correctAnswer = question[4]

    }

    fun checkAnswer(answerText: String){
        if(answerText == correctAnswer){
            binding.judgeImage.setImageResource(R.drawable.maru_image)
            correctCount++
        }else{
            binding.judgeImage.setImageResource(R.drawable.batu_image)
        }
        showAnswer()
        quizCount++
    }
    private fun showAnswer(){
        binding.correctAnswerText.text = "正解:$correctAnswer"
        binding.judgeImage.isVisible = true
        binding.nextButton.isVisible = true
        binding.answerButton1.isEnabled = false
        binding.answerButton2.isEnabled = false
        binding.answerButton3.isEnabled = false
    }
}