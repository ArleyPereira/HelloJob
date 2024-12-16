package br.com.hellodev.design.presenter.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: Painter,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("button_loading.json"))
//
//    Button(
//        onClick = onClick,
//        modifier = modifier
//            .height(58.dp)
//            .border(
//                width = 1.dp,
//                color = MovieStreamingTheme.colorScheme.borderColor,
//                shape = RoundedCornerShape(16.dp)
//            ),
//        enabled = !isLoading,
//        shape = RoundedCornerShape(16.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = MovieStreamingTheme.colorScheme.backgroundSocialButtonColor,
//        ),
//        content = {
//            if (isLoading) {
//                LottieAnimation(
//                    composition = composition,
//                    modifier = Modifier
//                        .size(70.dp),
//                    iterations = LottieConstants.IterateForever,
//                    maintainOriginalImageBounds = true,
//                    contentScale = ContentScale.Crop
//                )
//            } else {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    Icon(
//                        painter = icon,
//                        contentDescription = text,
//                        modifier = Modifier
//                            .size(24.dp),
//                        tint = Color.Unspecified
//                    )
//
//                    text?.let {
//                        Spacer(modifier = Modifier.width(12.dp))
//
//                        Text(
//                            text = text,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                lineHeight = 22.4.sp,
//                                fontFamily = UrbanistFamily,
//                                fontWeight = FontWeight.Bold,
//                                color = MovieStreamingTheme.colorScheme.textColor,
//                                textAlign = TextAlign.Center,
//                                letterSpacing = 0.2.sp
//                            )
//                        )
//                    }
//                }
//            }
//
//        }
//    )
}

@PreviewLightDark
@Composable
private fun SocialButtonPreview() {
//    HelloTheme {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(HelloTheme.colorScheme.screen.background)
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//            SocialButton(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                text = "Continuar com o Google",
//                icon = painterResource(id = R.drawable.ic_google),
//                isLoading = false,
//                onClick = {}
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            SocialButton(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                text = "Continuar com o Facebook",
//                icon = painterResource(id = R.drawable.ic_facebook),
//                isLoading = false,
//                onClick = {}
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            SocialButton(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                text = "Continuar com o Github",
//                icon = painterResource(id = R.drawable.ic_github),
//                isLoading = false,
//                onClick = {}
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                SocialButton(
//                    icon = painterResource(id = R.drawable.ic_google),
//                    isLoading = false,
//                    onClick = {}
//                )
//
//                Spacer(modifier = Modifier.width(20.dp))
//
//                SocialButton(
//                    icon = painterResource(id = R.drawable.ic_facebook),
//                    isLoading = false,
//                    onClick = {}
//                )
//
//                Spacer(modifier = Modifier.width(20.dp))
//
//                SocialButton(
//                    icon = painterResource(id = R.drawable.ic_github),
//                    isLoading = false,
//                    onClick = {}
//                )
//            }
//        }
//    }
}