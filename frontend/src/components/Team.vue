<script setup>
  import TeamPlayersVue from './TeamPlayers.vue';
  const props = defineProps({'id':Number, 'name':String, 'joueurs':Array});
</script>
<template>
    <div class="flex flex-row m-5 items-center">
        
        <TeamPlayersVue :lettre="props.name.substring(0,1)" :joueurs="props.joueurs" />

        <div class="flex flex-col text-xs">
            <span class="overflow-hidden hover:text-clip">{{props.name}}</span>
            <div class=" flex flex-row space-x-2 items-baseline">
                <i class="fa-thin fa-user"></i>
                <span>{{props.joueurs.length}}</span>
            </div>
        </div>
        
    </div>
</template>