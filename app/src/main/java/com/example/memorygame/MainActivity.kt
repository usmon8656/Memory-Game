package com.example.memorygame

import android.media.Image
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.ui.theme.MemoryGameTheme

class MainActivity : ComponentActivity() {
    val listImageOpenClose = arrayOf(false,false,false,false,false,false,)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.img1.setOnClickListener {
            imageClick(binding.img1 , R.drawable.img_1 , 0)
        }
        binding.img2.setOnClickListener {
            imageClick(binding.img2 , R.drawable.img_2 , 1)
        }
        binding.img3.setOnClickListener {
            imageClick(binding.img3 , R.drawable.img_3, 2)
        }
        binding.img4.setOnClickListener {
            imageClick(binding.img4 , R.drawable.img_1 , 3)
        }
        binding.img5.setOnClickListener {
            imageClick(binding.img5 , R.drawable.img_2 , 4)
        }
        binding.img6.setOnClickListener {
            imageClick(binding.img6 , R.drawable.img_3, 5)
        }


    }



    fun imageClick(imageView: ImageView, rasm: Int , index:Int){
        if (!animationDoing){
            if (listImageOpenClose[index]==false){
                animationOchilishi(imageView , rasm , index)
            }else{
                animationYopilishi(imageView, rasm , index )
            }
        }
    }



    fun animationOchilishi(imageView: ImageView, rasm:Int,index: Int){
        val animation = AnimationUtils.loadAnimation(this , R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationStart(animation: Animation?) {animationDoing = true}

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity ,R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listImageOpenClose[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++

                        if (ochiqRasm==2){
                            if (rasmId[0]==rasmId[1]){
                                imageViewAniqla(imageIndex[0]).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]).visibility = View.INVISIBLE
                                ochiqRasm--
                            }else{
                                animationYopilishi(imageViewAniqla(imageIndex[0]), -1 , imageIndex[0])
                                animationYopilishi(imageViewAniqla(imageIndex[1]), -1 , imageIndex[1])
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }



    fun animationYopilishi(imageView: ImageView, rasm:Int , index: Int?){
        val animation = AnimationUtils.loadAnimation(this , R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity ,R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.img)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listImageOpenClose[index!!] = false
        ochiqRasm--
    }

    fun imageViewAniqla(index: Int?):ImageView{
        var imageView:ImageView? = null
        when(index){
            0-> imageView = binding.img1
            1-> imageView = binding.img2
            2-> imageView = binding.img3
            3-> imageView = binding.img4
            4-> imageView = binding.img5
            5-> imageView = binding.img6
        }
        return imageView!!
    }
}