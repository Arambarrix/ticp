<script setup>
    import { computed } from 'vue'
    const props = defineProps({ 'id': Number, 'url': String});

    var cssVars = computed(() => {
        return {
            '--bg-color': props.color,
        }
    });
    function ConfirmMessage() {
        if (confirm("Vous voulez Supprimer l'élément?")) {
            // TO DO
        }
    }
</script>
<template>
    <div class="w-full rounded relative">
        <div to="" class="absolute text-center top-3 right-3 shadow-sm" @click="ConfirmMessage()">
            <i class=" h-8 w-8 p-2 rounded-full bg-red-900 fa-regular fa-trash text-white cursor-pointer font-bold" title="modifier"></i>
        </div>
        <img :src="url" alt="image" />
    </div>
</template>