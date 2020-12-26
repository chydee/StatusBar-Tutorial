package com.chydee.statusbarcolortutorial.destinations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chydee.statusbarcolortutorial.R

/**
 * @author: Desmond Ngwuta
 * @email: desmondchidi311@gmail.com
 * @created: 26/12/2020
 */

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}