package com.example.androidbp.theme

import android.content.Context
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.androidbp.R

@Composable
fun getLightAppColors() = Colors(
    primary = colorResource(id = R.color.red_primary),
    primaryVariant = colorResource(id = R.color.red_dark),
    secondary = colorResource(id = R.color.teal_200),
    secondaryVariant = colorResource(id = R.color.teal_700),
    background = colorResource(id = R.color.white),
    surface = colorResource(id = R.color.white),
    error= colorResource(id = R.color.red_primary),
    onPrimary= colorResource(id = R.color.white),
    onSecondary= colorResource(id = R.color.white),
    onBackground= colorResource(id = R.color.black),
    onSurface= colorResource(id = R.color.black),
    onError= colorResource(id = R.color.red_primary),
    isLight = true,
)

@Composable
fun getDarkAppColors() = Colors(
    primary = colorResource(id = R.color.red_light),
    primaryVariant = colorResource(id = R.color.red_primary),
    secondary = colorResource(id = R.color.teal_200),
    secondaryVariant = colorResource(id = R.color.teal_700),
    background = colorResource(id = R.color.black),
    surface = colorResource(id = R.color.black),
    error= colorResource(id = R.color.red_primary),
    onPrimary= colorResource(id = R.color.white),
    onSecondary= colorResource(id = R.color.white),
    onBackground= colorResource(id = R.color.white),
    onSurface= colorResource(id = R.color.white),
    onError= colorResource(id = R.color.red_primary),
    isLight = false,
)
