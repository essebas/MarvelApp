package com.zebas2.marvelapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.zebas2.marvelapp.data.util.Prefers
import com.zebas2.marvelapp.databinding.FragmentCharacterDetailWebViewBinding

private const val TAG = "CharacterDetailWebViewF"

class CharacterDetailWebViewFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDetailWebViewBinding.bind(view)
        val args: CharacterDetailFragmentArgs by navArgs()
        val character = args.selectedCharacter
        binding.wvDetail.apply {
            val url = character.urls?.first { it.type == "detail" }
            val urlString = url?.url?.replace(
                "http",
                "https"
            )
            Log.i(TAG, "onViewCreated: $urlString")
            webViewClient = WebViewClient()
            if (urlString != null) {
                loadUrl(
                    urlString
                )
            }
        }
    }


}