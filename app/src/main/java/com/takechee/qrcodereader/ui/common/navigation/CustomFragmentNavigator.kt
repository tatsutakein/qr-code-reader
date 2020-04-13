package com.takechee.qrcodereader.ui.common.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@Navigator.Name("fragment")
class CustomFragmentNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val transaction = manager.beginTransaction()

        val currentFragment = manager.primaryNavigationFragment
        if (currentFragment != null) {
            transaction.detach(currentFragment)
        }

        val fragment = getFragment(destination, transaction)

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()

        return destination
    }

    private fun getFragment(
        destination: Destination,
        transaction: FragmentTransaction
    ): Fragment {
        val tag = destination.id.toString()

        // Find fragment
        val findFragment = manager.findFragmentByTag(tag)
        if (findFragment != null) {
            transaction.attach(findFragment)
            return findFragment
        }

        // New fragment
        val className = destination.className.let { className ->
            if (className[0] == '.') {
                context.packageName + className
            } else {
                className
            }
        }
        val newFragment = manager.fragmentFactory.instantiate(context.classLoader, className)
        transaction.add(containerId, newFragment, tag)
        return newFragment
    }
}