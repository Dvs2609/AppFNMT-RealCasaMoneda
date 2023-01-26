package com.example.apprealcasamoneda.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.*
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrOfficeLocatorBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FrOfficeLocatorBinding

        /**
 * A simple [Fragment] subclass.
 * Use the [LocalizadorOficinas.newInstance] factory method to
 * create an instance of this fragment.
 */
class OfficeLocator : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrOfficeLocatorBinding.inflate(inflater, container, false)

        binding.rectangularLYSearchOffice.setOnClickListener{
            show(binding.ofLocFilterRL)
            binding.officeLocatorCloseCross.visibility = View.VISIBLE
        }

        binding.officeLocatorCloseCross.setOnClickListener {
            hide(binding.ofLocFilterRL)
            binding.officeLocatorCloseCross.visibility = View.GONE
        }

        //webView.loadUrl("https://mapaoficinascert.appspot.com")
       // webView.loadDataWithBaseURL(null,  "javascript:(function() { document.getElementById('searchBoxDiv').style; })();", "text/html", "UTF-8", null)

        /*webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                /*webView.loadUrl("javascript:(function() { " +
                        "document.getElementsById('addressBox')[0].style.display='none'; " +
                        "})()");*/

                webView.loadUrl("javascript:var x = document.getElementById('addressBox')[0].style.display='none';");
            }
        }*/
        val webView = binding.mapWebView
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.evaluateJavascript("document.getElementById('addressBox').style.display='none';") {
                }
            }

            //para redirigir
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (request != null) {
                    view?.loadUrl(request.url.toString())
                }
                return true
            }

        }
        webView.loadUrl("https://mapaoficinascert.appspot.com")


        val redBackground = AppCompatResources.getDrawable(requireContext(), R.drawable.filter_shapes_red_of_loc)
        val blueBackground = AppCompatResources.getDrawable(requireContext(), R.drawable.filter_shapes_blue_of_loc)
        val greenBackground = AppCompatResources.getDrawable(requireContext(), R.drawable.filter_shapes_green_of_loc)
        val yellowBackground = AppCompatResources.getDrawable(requireContext(), R.drawable.filter_shapes_yellow_of_loc)

        val filterType1 = binding.filterType1
        val filterType2 = binding.filterType2
        val filterType3 = binding.filterType3
        val filterType4 = binding.filterType4

        changeBg(filterType1,redBackground)
        changeBg(filterType2,blueBackground)
        changeBg(filterType3,greenBackground)
        changeBg(filterType4,yellowBackground)

        return binding.root
    }

    private fun changeBg(filter: RelativeLayout?, color: Drawable?){
        val greyBackground = AppCompatResources.getDrawable(requireContext(), R.drawable.filter_grey_shape_of_loc)
        filter?.setOnClickListener {
            if (filter.background == greyBackground) {
                filter.background = color
            } else {
                filter.background = greyBackground
            }
        }
    }
    private fun show(relativeLayout: RelativeLayout?) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (relativeLayout != null) {
            if (relativeLayout.visibility == View.GONE ){

                relativeLayout.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.VISIBLE

            }
        }
    }

    private fun hide(relativeLayout: RelativeLayout?) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (relativeLayout != null) {
            if (relativeLayout.visibility == View.VISIBLE ){

                relativeLayout.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.GONE

            }
        }
    }


            companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LocalizadorOficinas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OfficeLocator().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}