<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <title>Редактирование задачи</title>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-2xl mx-auto bg-white shadow-md rounded-lg overflow-hidden">
    <div class="bg-blue-500 p-4">
        <div class="flex justify-between items-center">
            <h1 class="text-2xl font-bold text-white">Редактирование задачи</h1>
            <a href="/day?dayId=${task.dayNum}" class="bg-white text-blue-500 py-2 px-4 rounded hover:bg-blue-50 transition-colors">
                ← Назад
            </a>
        </div>
    </div>

    <div class="p-6">
        <#include "error-success.ftlh">
        <form action="/edit-task?taskId=${task.id}&dayId=${task.dayNum}" method="post" class="space-y-4">
            <input type="hidden" name="taskId" value="${task.id}">
            <input type="hidden" name="dayNum" value="${task.dayNum}">

            <div>
                <label for="title" class="block text-sm font-medium text-gray-700 mb-1">Название задачи</label>
                <input type="text" id="title" name="title" value="${task.title}"
                       class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div>
                <label for="taskType" class="block text-sm font-medium text-gray-700 mb-1">Тип задачи</label>
                <select id="type" name="taskType"
                        class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="TASK" <#if task.taskType == "TASK">selected</#if>>Обычная задача</option>
                    <option value="URGENT" <#if task.taskType == "URGENT">selected</#if>>Срочное дело</option>
                    <option value="WORK" <#if task.taskType == "WORK">selected</#if>>Работа</option>
                    <option value="SHOPPING" <#if task.taskType == "SHOPPING">selected</#if>>Покупки</option>
                    <option value="OTHER" <#if task.taskType == "OTHER">selected</#if>>Прочее</option>
                </select>
            </div>

            <div>
                <label for="description" class="block text-sm font-medium text-gray-700 mb-1">Описание</label>
                <textarea id="description" name="description" rows="3"
                          class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">${task.getDescription()}</textarea>
            </div>

            <div class="flex space-x-3 pt-4">
                <button type="submit"
                        class="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded transition-colors">
                    Сохранить изменения
                </button>
                <a href="/day?dayId=${task.dayNum}"
                   class="inline-block bg-gray-300 hover:bg-gray-400 text-gray-800 py-2 px-4 rounded transition-colors">
                    Отмена
                </a>
            </div>
        </form>
    </div>

    <div class="p-6 bg-gray-50 border-t border-gray-200">
        <div class="flex items-center justify-between">
            <h3 class="text-lg font-medium text-gray-700">Удаление задачи</h3>
            <a href="/delete-task?taskId=${task.id}"
               class="bg-red-500 hover:bg-red-600 text-white py-2 px-4 rounded transition-colors">
                Удалить задачу
            </a>
        </div>
        <p class="text-sm text-gray-500 mt-1">Это действие нельзя отменить. Задача будет удалена безвозвратно.</p>
    </div>
</div>
</body>
</html>