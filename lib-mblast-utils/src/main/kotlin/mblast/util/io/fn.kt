package mblast.util.io

import java.io.InputStream
import java.io.OutputStream


fun InStream.toJDKStream(): InputStream =
  if (this is InputStream) this else IOInputStream(this)

fun OutStream.toJDKStream(): OutputStream =
  if (this is OutputStream) this else IOOutputStream(this)

fun InputStream.toIOStream(): InStream =
  if (this is InStream) this else InputIOStream(this)

fun OutputStream.toIOStream(): OutStream =
  if (this is OutStream) this else OutputIOStream(this)