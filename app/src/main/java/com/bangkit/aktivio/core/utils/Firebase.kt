package com.bangkit.aktivio.core.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

object Firebase {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val user: FirebaseUser? = auth.currentUser
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
}