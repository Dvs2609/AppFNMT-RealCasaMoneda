package com.example.apprealcasamoneda.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.KeyEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.apprealcasamoneda.fragments.PhysicalPersonCertificates.PhysicalPerson

open class BaseFragment : Fragment() {

    open fun showHide(relativeLayout: RelativeLayout?, imageView: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (relativeLayout != null) {
            if (relativeLayout.visibility == View.GONE ){

                relativeLayout.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.VISIBLE
                imageView.rotation = 90f


            }else{

                //textView.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.GONE
                imageView.rotation = 0f
            }
        }
    }
}