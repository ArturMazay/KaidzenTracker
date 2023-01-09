package com.aistudio.kaidzentracker.android.screensimport android.widget.Toastimport androidx.compose.foundation.ExperimentalFoundationApiimport androidx.compose.foundation.backgroundimport androidx.compose.foundation.layout.Boximport androidx.compose.foundation.layout.fillMaxSizeimport androidx.compose.foundation.layout.paddingimport androidx.compose.foundation.lazy.LazyColumnimport androidx.compose.runtime.*import androidx.compose.runtime.livedata.observeAsStateimport androidx.compose.ui.Alignmentimport androidx.compose.ui.Modifierimport androidx.compose.ui.graphics.Colorimport androidx.compose.ui.platform.LocalContextimport androidx.compose.ui.unit.dpimport androidx.navigation.NavControllerimport com.aistudio.kaidzentracker.android.screens.commonScreenComponents.*import com.aistudio.kaidzentracker.android.theme.PrimaryVariantimport com.aistudio.kaidzentracker.android.ui.viewmodels.FlowScreenViewModelimport com.aistudio.kaidzentracker.android.ui.viewmodels.models.ApplicationIntentimport org.koin.androidx.compose.koinViewModelimport kotlin.math.floor@OptIn(ExperimentalFoundationApi::class)@Composablefun FlowScreen(    modifier: Modifier,    navController: NavController,    flowScreenViewModel: FlowScreenViewModel = koinViewModel(),) {    val state = flowScreenViewModel.flowScreenState.observeAsState().value    val context = LocalContext.current    var visible by remember { mutableStateOf(false) }    when (state!!.isLoading) {        false -> Box(modifier = modifier            .fillMaxSize()            .background(color = Color.White)) {            LazyColumn(modifier = modifier.padding(bottom = 45.dp)) {                item {                    CommonLogoAppBar(modifier = modifier, isAnimated = visible)                }                stickyHeader {                    CommonHeaderText(                        modifier = modifier,                        textHeader = "ВЫСТРАИВАНИЕ ПОТОКА",                        colorBackgraund = PrimaryVariant,                        height = 28,                        shape = 5,                        paddingHorizontal = 6                    )                }                item {                    CommonTableHeader(modifier = modifier)                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.firstQuestions.toInt(),                        onValueChange = { firstQuestions ->                            flowScreenViewModel.getIntent(intent = ApplicationIntent.ChangedFirstQuestions(                                newResult = firstQuestions))                        },                        numberQuestions = "1",                        textQuestions = "Процент технологических и бизнес процессов, разделенных на операции с заданным тактом.",                        textRecommendation = "1. Разделить процессы на операции\n" +                                "2. Определить для каждой операции поставщика, владельца и заказчика\n" +                                "3. Определить для каждой операции время такта и время цикла")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.secondQuestions.toInt(),                        onValueChange = { secondQuestions ->                            flowScreenViewModel.getIntent(intent = ApplicationIntent.ChangedSecondQuestions(                                newResult = secondQuestions))                        },                        numberQuestions = "2",                        textQuestions = "Процент рабочих мест, оборудованных в соответствии с технологической картой",                        textRecommendation = "Оптимизировать оборудование рабочих мест в соответствии с технологическими картами операций")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.threeQuestions.toInt(),                        onValueChange = { threeQuestions ->                            flowScreenViewModel.getIntent(intent = ApplicationIntent.ChangedThreeQuestions(                                newResult = threeQuestions))                        },                        numberQuestions = "3",                        textQuestions = "Процент сотрудников, умеющих определять процессы, операции и действия создающие и не создающие ценность",                        textRecommendation = "1. Провести занятия с сотрудниками по навыкам определения ценности процессов и операций.\n" +                                "2. Периодически проводить опрос случайно выбранных сотрудников на умение определить ценность процесса/операции.")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.fourQuestions.toInt(),                        onValueChange = { fourQuestions ->                            flowScreenViewModel.getIntent(intent = ApplicationIntent.ChangedFourQuestions(                                newResult = fourQuestions))                        },                        numberQuestions = "4",                        textQuestions = "Процент рабочих мест, где используется система\n" +                                "Канбан, пополнение материалов на рабочем месте происходит строго по мере необходимости",                        textRecommendation = "1. Разработать план внедрения элементов системы Канбан\n" +                                "2. Внедрить элементы системы Канбан на складах")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.fiveQuestions.toInt(),                        onValueChange = { fiveQuestions ->                            flowScreenViewModel.getIntent(intent = ApplicationIntent.ChangedFiveQuestions(                                newResult = fiveQuestions))                        },                        numberQuestions = "5",                        textQuestions = "Процент процессов, для которых оптимизированы\n" +                                "маршруты движения работников и транспортировки материалов и продукции",                        textRecommendation = "1. Провести анализ карт спагетти процессов и операций\n" +                                "2. Разработать предложения по оптимизации\n" +                                "маршрутов движения работников и транспортировки материалов и продукции")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.sixQuestions.toInt(),                        onValueChange = { sixQuestions ->                            flowScreenViewModel.getIntent(intent = ApplicationIntent.ChangedSixQuestions(                                newResult = sixQuestions))                        },                        numberQuestions = "6",                        textQuestions = "Процент процессов, в которых функционирует процедура оперативного реагирования на отклонения",                        textRecommendation = "Периодически проводить аудит функционирования процедур оперативного реагирования на отклонения в процессах")                }                item {                    val result = floor(state.result * 10) / 10.0                    ResultView(modifier = modifier,                        textResult = result.toString(),                        text = "Итог")                }                item {                    val newResult = floor(state.newResult * 10) / 10.0                    ResultView(modifier = modifier,                        textResult = newResult.toString(),                        text = "Предыдущее значение")                }            }                    CommonButtons(                        modifier = modifier.align(Alignment.BottomCenter),                        onClickSave = {                            Toast.makeText(context, "Сохранено", Toast.LENGTH_SHORT).show()                            flowScreenViewModel.getIntent(intent = ApplicationIntent.SaveClicked)                        },                        onClickDelete = {                            Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show()                            flowScreenViewModel.getIntent(intent = ApplicationIntent.DeletedClicked)                                        },                        onClickNext = { navController.navigate(route = "staffInvolvementScreen") },                        onClickBack = { navController.popBackStack() }                        ,navController = navController                    )                }        true -> FullScreenLoaderComponent(modifier = modifier)    }    LaunchedEffect(key1 = Unit, block = {        flowScreenViewModel.getIntent(intent = ApplicationIntent.InitialScreen)        visible = true    })}