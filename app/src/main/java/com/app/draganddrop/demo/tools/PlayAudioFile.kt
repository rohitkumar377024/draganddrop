package com.app.draganddrop.demo.tools

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.app.draganddrop.R
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.play_audio_file_layout.view.*
import kotlinx.android.synthetic.main.play_audio_file_properties_boss_layout.view.*
import java.io.IOException

class PlayAudioFile : RelativeLayout {

    private var mMediaRecorder: MediaRecorder? = null
    private var mPlayer: MediaPlayer? = null
    private var isRecordingDone: Boolean = false
    private var isPlaybackDone: Boolean = false
    private lateinit var mFile: String
    private val REQUEST_SELECT_AUDIO_CLIP = 123
    private lateinit var mActivity: Activity

    constructor(context: Context?) : super(context) { setupProperties() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)  { setupProperties() }

    private fun setupProperties() {
        inflate(context, R.layout.play_audio_file_layout, this)

        //play_audio_file_title_txtview.setOnTouchListener(OnDragTouchListener(this, play_audio_file_title_txtview))

//        play_audio_file_make_changes_btn.setOnClickListener {
//            play_audio_file_bottom_ll.visibility = View.GONE
//        }


        mActivity = context as Activity

        //Initializing all systems at start
        init()
    }

    fun init() {
        checkPermissions()
        initVisibility()
        initMediaPlayer()
        initFileLocation()
        handleSelectAudioClipButton()
        handleRecordButton()
        handleDoneButton()
        handleMakeChangesButton()
        handleRecordingAndPlaybackFeatures(mFile)
        handleRecordingButtonState()
    }

    //Handle results for Selecting Audio Clip
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //For Selecting Audio From Storage
        if (requestCode == REQUEST_SELECT_AUDIO_CLIP) { //1 ->> Select Audio Clip
            if (resultCode == Activity.RESULT_OK) {
                //Selected Audio Clip
                val uri = data?.data

                if (uri != null) {

                    init() //todo --> Write some documentation for here and below and this script


                    hide(play_audio_file_select_audio_clip_btn)
                    hide(play_audio_file_record_audio_btn)

                    doneTitleText()

                    show(play_audio_file_make_changes_ll)

                    play_audio_file_make_changes_btn.setOnClickListener {
                        show(play_audio_file_top_ll)

                        show(play_audio_file_select_audio_clip_btn)
                        show(play_audio_file_record_audio_btn)

                        hide(play_audio_file_make_changes_ll)

                        defaultTitleText()
                    }
                }
            }
        }
    }

    //Title Text State Handling Functions Below
    private fun defaultTitleText() { play_audio_file_title_txtview.text = "Play an Audio File" }
    private fun doneTitleText() { play_audio_file_title_txtview.text = "Audio File Done" }

    private fun checkPermissions() { //Requesting Permission RECORD_AUDIO /* first priority */
        Permissions.check(context, Manifest.permission.RECORD_AUDIO, null, object : PermissionHandler() {
            override fun onGranted() {/* no need to even show a toast here */ } }) }

    private fun initVisibility() { /* Visibility States at Start */
        hide(play_audio_file_bottom_ll)   //Initially Bottom LL ->> Visibility GONE
        hide(play_audio_file_make_changes_ll)   //Initially Make Changes LL ->> Visibility GONE
    }

    private fun initMediaPlayer() {
        //Initializing the MediaPlayer ->> This change helped to fix stopping problem.
        //One instance = No problems. :)
        mPlayer = MediaPlayer()
    }

    private fun initFileLocation() { /* File location for saving the recorded audio */
        mFile = context.filesDir.absolutePath + "/audio_test.3gp"
    }

    private fun handleSelectAudioClipButton() { /* Handles selecting of audio clip */
        play_audio_file_select_audio_clip_btn.setOnClickListener {
            mActivity.startActivityForResult(selectAudioClipIntent(), REQUEST_SELECT_AUDIO_CLIP)
        }
    }

    private fun selectAudioClipIntent(): Intent { /*This function creates the intent for selecting the audio clip */
        val selectAudioClipIntent = Intent()
        selectAudioClipIntent.type = "audio/*"
        selectAudioClipIntent.action = Intent.ACTION_GET_CONTENT
        return selectAudioClipIntent
    }

    private fun handleRecordButton() { /* Handles Recording Button */
        //Once Record Button Clicked --> Top LL Visibility GONE, Bottom LL Visible
        play_audio_file_record_audio_btn.setOnClickListener {
            hide(play_audio_file_top_ll)
            show(play_audio_file_bottom_ll)
        }
    }

    private fun handleDoneButton() { /* Handles Done Button */
        play_audio_file_done_btn.setOnClickListener {
            hide(play_audio_file_bottom_ll) //Hides Recording Button, Playback Button and Done Button
            doneTitleText()
            show(play_audio_file_make_changes_ll) //Show Make Changes LL Here
        }
    }

    private fun handleMakeChangesButton() { /* Handles Making Changes Button */
        play_audio_file_make_changes_btn.setOnClickListener {
            show(play_audio_file_top_ll)
            hide(play_audio_file_make_changes_ll)
            defaultTitleText() //Change the text to default
        }
    }

    private fun handleRecordingAndPlaybackFeatures(mFile: String) { /* Handles Recording and Playback Completely */
        //Recording Audio feature
        play_audio_file_recording_btn.setOnCheckedChangeListener { _, isChecked -> startOrStopRec(isChecked) }
        //Playback of Recorded Audio feature
        play_audio_file_playback_btn.setOnCheckedChangeListener { _, isChecked -> playOrStop(isChecked, mFile) }
    }

    //Handles recording functionality completely
    private fun startOrStopRec(record: Boolean) {
        if (record) { startMediaRecorder() }
        else { stopMediaRecorder() }
        handleRecordingButtonState() //Handle recording state in this function
    }

    private fun startMediaRecorder() { /* Starts Media Recorder */
        mMediaRecorder = MediaRecorder()
        mMediaRecorder?.reset()
        mMediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mMediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mMediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mMediaRecorder?.setOutputFile(mFile) //Passing File Path
        //Try-Catch
        try { mMediaRecorder?.prepare() }
        catch (e: IOException) { e.printStackTrace() }
        //Start recording
        mMediaRecorder?.start()
        isRecordingDone = false //Setting Recording State
    }

    private fun stopMediaRecorder() { /*  // Stops Media Recorder */
        if (mMediaRecorder != null) {
            mMediaRecorder?.stop()
            mMediaRecorder?.release()
            mMediaRecorder = null
            isRecordingDone = true //Setting Recording State
        }
    }

    private fun playOrStop(play: Boolean, mFile: String) { /* Handles playing and stopping of media player completely */
        try {
            if (play) {
                initMediaPlayer() //This quickfix allows us to re-start media player when user wants to re-playback
                startMediaPlayer(mFile) //Starting MediaPlayer
                isPlaybackDone = false //Setting Playback State
                mPlayer?.setOnCompletionListener { stopMediaPlayer() } /* Stopping after full audio playback */
                handlePlaybackButtonState() //Handle playback state in this function
            }
            if (!play) { stopMediaPlayer() } /* Stopping here */
        } catch (e: IOException) { Log.e("PlayAudioFileAudioUtils", e.message) }
    }

    private fun startMediaPlayer(mFile: String) { /* Starting the Media Player */
        mPlayer?.setDataSource(mFile)
        mPlayer?.prepare()
        mPlayer?.start()
    }

    private fun stopMediaPlayer() { /* Stopping the Media Player */
        mPlayer?.stop(); mPlayer?.release(); mPlayer = null
        play_audio_file_playback_btn.isChecked = false //Changing Toggle Button State
        isPlaybackDone = true //Setting Playback State
        handlePlaybackButtonState() //Handle playback state in this function
    }

    private fun handleRecordingButtonState() { /* Handles Recording Button States */
        if (!isRecordingDone) {
            hide(play_audio_file_playback_btn)
            hide(play_audio_file_done_btn)
        } else {
            show(play_audio_file_playback_btn)
            show(play_audio_file_done_btn)
            play_audio_file_recording_btn.textOff = "Re-Record Audio"
        }
    }

    private fun handlePlaybackButtonState() { /* Handles Playback Button States */
        if (!isPlaybackDone) {
            disable(play_audio_file_recording_btn)
            disable(play_audio_file_done_btn)
        } else {
            enable(play_audio_file_recording_btn)
            enable(play_audio_file_done_btn)
        }
    }

    private fun enable(view: View) { view.alpha = 1f; view.isClickable = true }
    private fun disable(view: View) { view.alpha = 0.4f; view.isClickable = false }
    private fun hide(view: View) { view.visibility = View.GONE }
    private fun show(view: View) { view.visibility = View.VISIBLE }

}