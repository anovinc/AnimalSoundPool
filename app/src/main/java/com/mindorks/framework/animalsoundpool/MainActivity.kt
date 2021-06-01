package com.mindorks.framework.animalsoundpool

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mindorks.framework.animalsoundpool.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    var mSoundMap: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        loadSounds()
    }

    private fun setUpUI() {
        this.binding.ibCat.setOnClickListener(this)
        this.binding.ibCock.setOnClickListener(this)
        this.binding.ibCow.setOnClickListener(this)
        this.binding.ibGorila.setOnClickListener(this)
        this.binding.ibDog.setOnClickListener(this)
        this.binding.ibSheep.setOnClickListener(this)
    }

    private fun loadSounds() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.cow] = this.mSoundPool.load(this, R.raw.cow, 1)
        this.mSoundMap[R.raw.cat] = this.mSoundPool.load(this, R.raw.cat, 1)
        this.mSoundMap[R.raw.cock] = this.mSoundPool.load(this, R.raw.cock, 1)
        this.mSoundMap[R.raw.dog] = this.mSoundPool.load(this, R.raw.dog, 1)
        this.mSoundMap[R.raw.sheep] = this.mSoundPool.load(this, R.raw.sheep, 1)
        this.mSoundMap[R.raw.monkey] = this.mSoundPool.load(this, R.raw.monkey, 1)
    }
    override fun onClick(v: View) {
        if (this.mLoaded == false) return
        when (v.getId()) {
            R.id.ib_cat -> playSound(R.raw.cat)
            R.id.ib_dog -> playSound(R.raw.dog)
            R.id.ib_cock -> playSound(R.raw.cock)
            R.id.ib_cow -> playSound(R.raw.cow)
            R.id.ib_gorila -> playSound(R.raw.monkey)
            R.id.ib_sheep -> playSound(R.raw.sheep)
        }
    }
    fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }

}