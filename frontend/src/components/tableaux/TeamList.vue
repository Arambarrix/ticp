<script setup>
    import { computed } from "vue";

    const props = defineProps({'classements':Object});
  
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
                                    Victoires
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">

                            <tr v-for="classement in classements" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200 whitespace-nowrap hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                <td class="px-6 py-3 text-center text-sm font-medium text-gray-900 w-1/5">{{classement.rang}}</td>
                                <td class="text-sm text-gray-900 font-light px-6 py-3 overflow-hidden h w-2/5">
                                    {{classement.equipe.nom}}
                                </td>
                                <td class="text-sm text-gray-900 font-light px-6 py-3 w-1/5">
                                    {{classement.points}}
                                </td>
                                <td class="bg-white px-1"></td>
                                <td class="text-sm text-gray-900 font-light px-6 py-3 text-center w-1/5">
                                    {{classement.victoires}} 
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>