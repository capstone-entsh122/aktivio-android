package com.bangkit.aktivio.modules.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.ArticleModel
import com.bangkit.aktivio.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = intent.getParcelableExtra<ArticleModel>("article")
        with(binding){
            data.let {
                tvTitle.text = it?.title
                tvAuthorDate.text = "By ${it?.author} - ${it?.createdAt}"
                tvContent.text = it?.content
                ivThumbnail.setImageResource(it?.thumbnail ?: R.drawable.img_placeholder)
            }
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}