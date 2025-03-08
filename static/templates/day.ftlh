<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <title>Задачи на ${day.getDay()}</title>
</head>

<body class="bg-gray-100 p-6">
<div class="max-w-3xl mx-auto bg-white shadow-md rounded-lg">
    <div class="bg-blue-500 p-4 rounded-t-lg">
        <div class="flex justify-between items-center">
            <h1 class="text-2xl font-bold text-white">Задачи на ${day.getDay()}</h1>
            <a href="/" class="bg-white text-blue-500 py-2 px-4 rounded hover:bg-blue-50 transition-colors">
                ← Назад к календарю
            </a>
        </div>
    </div>

    <div class="p-6">
        <h2 class="text-xl font-semibold text-gray-800 mb-4">Текущие задачи</h2>
        <#include "error-success.ftlh">
        <#if tasks?? && tasks?size gt 0>
            <div class="space-y-4">
                <#list tasks as task>
                    <div class="p-4 bg-gray-50 rounded-lg shadow-md" style="border-left: 4px solid ${task.taskType.getColorCode()}">
                        <div class="flex justify-between items-start">
                            <div>
                                <div class="flex items-center gap-3 mb-2">
                                    <h3 class="font-semibold text-lg">${task.getTitle()}</h3>

                                    <span class="inline-block px-2 py-1 text-xs font-medium rounded-full"
                                          style="background-color: ${task.taskType.getColorCode()}20; color: ${task.taskType.getColorCode()}">
                                            ${task.type}
                                        </span>
                                </div>

                                <p class="text-gray-600">${task.getDescription()}</p>
                            </div>

                            <div class="flex space-x-2">
                                <a href="/edit-task?taskId=${task.getId()}"
                                   class="text-gray-500 hover:text-blue-500 transition-colors">
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-5 h-5">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                                    </svg>
                                </a>

                                <a href="/delete-task?taskId=${task.getId()}"
                                   class="text-gray-500 hover:text-red-500 transition-colors">
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-5 h-5">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                                    </svg>
                                </a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <div class="text-center p-8 bg-gray-50 rounded-lg">
                <p class="text-gray-500 text-lg">На этот день задач пока нет</p>
            </div>
        </#if>
        <div>
            <p class="text-gray-400 mt-5">Нажмите на кнопку ниже, чтобы добавить новую задачу</p>
        </div>
        <div class="mt-8 flex justify-center">
            <a href="/add-task?dayId=${day.day}" class="bg-green-500 hover:bg-green-600 text-white py-3 px-6 rounded-lg shadow-md transition-colors flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-5 h-5 mr-2">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path>
                </svg>
                Добавить новую задачу
            </a>
        </div>
    </div>
</div>
</body>
</html>