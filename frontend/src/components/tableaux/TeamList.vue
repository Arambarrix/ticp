<script setup>

const props = defineProps({'data':Object});

</script>

<template>
    <div class="flex flex-col">
        <div class="overflow-x-auto sm:mx-0.5 lg:mx-0.5">
            <div class="py-2 inline-block min-w-full sm:px-2 lg:px-4">
                <div class="overflow-hidden">
                    <table class="min-w-full flex flex-col space-y-2 table-auto">
                        <thead class="bg-green-700 text-white font-bold uppercase rounded-md">
                            <tr class="flex flex-row justify-between ">
                                <th scope="col" class="text-sm px-6 py-3 text-center w-1/5">
                                    Rang
                                </th>
                                <th scope="col" class="text-sm px-6 py-3 text-left w-2/5">
                                    Equipes
                                </th>
                                <th scope="col" class="text-sm px-6 py-3 text-left w-1/5">
                                    Points
                                </th>
                                <th scope="col" class="bg-white px-1"></th>
                                <th scope="col" class="text-sm px-6 py-3 text-center w-1/5">
                                    Match
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">

                            <tr v-for="equipe in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200">
                                <td class="px-6 py-3 text-center whitespace-nowrap text-sm font-medium text-gray-900 w-1/5">{{equipe.rang}}</td>
                                <td class="text-sm text-gray-900 font-light px-6 py-3 whitespace-nowrap overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal w-2/5">
                                    {{equipe.nom}}
                                </td>
                                <td class="text-sm text-gray-900 font-light px-6 py-3 whitespace-nowrap w-1/5">
                                    {{equipe.point}}
                                </td>
                                <td class="bg-white px-1"></td>
                                <td class="text-sm text-gray-900 font-light px-6 py-3 whitespace-nowrap text-center w-1/5">
                                    {{equipe.matchJoue}} / {{equipe.matchTotal}}
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>