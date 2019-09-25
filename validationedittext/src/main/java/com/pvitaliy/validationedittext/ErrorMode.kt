package com.pvitaliy.validationedittext

public enum class ErrorModeConstant(val mode: ErrorMode) {
    None(ErrorMode.None), OnUserInput(ErrorMode.OnUserInput), Always(ErrorMode.Always)
}

public sealed class ErrorMode {
    public object None : ErrorMode()
    public object OnUserInput : ErrorMode()
    public object Always : ErrorMode()
    public class Once(val nextMode: ErrorMode) : ErrorMode()
}