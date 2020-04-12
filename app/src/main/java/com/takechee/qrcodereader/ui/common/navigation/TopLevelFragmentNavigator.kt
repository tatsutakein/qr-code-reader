package com.takechee.qrcodereader.ui.common.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

class TopLevelFragmentNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

//    override fun navigate(destination: Destination, args: Bundle?, navOptions: NavOptions?) {
//        val tag = destination.id.toString()
//        val transaction = manager.beginTransaction()
//
//        val currentFragment = manager.primaryNavigationFragment
//        if (currentFragment != null) {
//            transaction.detach(currentFragment)
//        }
//
//        var fragment = manager.findFragmentByTag(tag)
//        if (fragment == null) {
//            fragment = destination.createFragment(args)
//            transaction.add(containerId, fragment, tag)
//        } else {
//            transaction.attach(fragment)
//        }
//
//        transaction.setPrimaryNavigationFragment(fragment)
//        transaction.setReorderingAllowed(true)
//        transaction.commit()
//
//        dispatchOnNavigatorNavigated(destination.id, BACK_STACK_DESTINATION_ADDED)
//    }
//
//    override fun navigate(
//        destination: Destination,
//        args: Bundle?,
//        navOptions: NavOptions?,
//        navigatorExtras: Navigator.Extras?
//    ): NavDestination? {
//        val tag = destination.id.toString()
//        val transaction = manager.beginTransaction()
//
//        val currentFragment = manager.primaryNavigationFragment
//        if (currentFragment != null) {
//            transaction.detach(currentFragment)
//        }
//
//        var fragment = manager.findFragmentByTag(tag)
//        if (fragment == null) {
//            fragment = destination.createFragment(args)
//            transaction.add(containerId, fragment, tag)
//        } else {
//            transaction.attach(fragment)
//        }
//
//        transaction.setPrimaryNavigationFragment(fragment)
//        transaction.setReorderingAllowed(true)
//        transaction.commit()
//
//        dispatchOnNavigatorNavigated(destination.id, BACK_STACK_DESTINATION_ADDED)
//
//        return super.navigate(destination, args, navOptions, navigatorExtras)
//    }
}