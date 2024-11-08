<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-25" style="max-width:100%;">
        <h2 class="text-center mb-3">Добавить сотрудника</h2>
        <form @submit.prevent="addEmployee">
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="name" class="form-label">ФИО</label>
              <input id="name" type="text" name="name" class="form-control" placeholder="ФИО" required v-model="employee.name">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="note" class="form-label">Примечание</label>
              <input id="note" type="text" name="note" class="form-control" placeholder="Примечание" required v-model="employee.note">
            </div>
          </div>
          <label for="profession" class="form-label">Профессия</label>
          <div v-for="profession in professions" :key="profession.id">
            <div class="form-check">
              <input class="form-check-input" type="radio" name="profession" :id="profession.name" :value="profession.id" v-model="employee.professionId">
              <label class="form-check-label" :for="profession.name">{{ profession.name }}</label>
            </div>
          </div>

          <label for="department" class="form-label">Отдел</label>
          <select id="department" class="form-control" v-model="employee.departmentId" required>
            <option value="">Отдел</option>
            <option v-for="department in departments" :key="department.id" :value="department.id">{{ department.name }}</option>
          </select>

          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Добавить">
            </div>
          </div>

          <div>
            <p v-if="message" class="text-success">{{ message }}</p>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'AddEmployee',
  components: {
    Navbar
  },

  data() {
    return {
      employee: {
        name: '',
        note: '',
        professionId: null,
        departmentId: null
      },
      professions: [],
      departments: [],
      message: ''
    };
  },

  beforeMount() {
    this.getProfessions();
    this.getDepartments();
  },

  methods: {
    addEmployee() {
      fetch('http://localhost:7000/employeesApi/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.employee)
      })
          .then(response => {
            if (response.ok) {
              this.message = 'Сотрудник успешно добавлен!';
              this.clearForm();
              this.$router.push("/employeesHome");
            } else {
              throw new Error('Ошибка при добавлении сотрудника');
            }
          })
          .catch(error => {
            console.error('Error adding employee:', error);
            this.message = 'Произошла ошибка при добавлении сотрудника.';
          });
    },

    getProfessions() {
      fetch('http://localhost:7000/professionsApi/professions')
          .then(res => res.json())
          .then(data => {
            this.professions = data;
            console.log(data);
          });
    },

    getDepartments() {
      fetch('http://localhost:7000/departmentsApi/departments')
          .then(res => res.json())
          .then(data => {
            this.departments = data;
            console.log(data);
          })
          .catch(error => console.error('Error fetching departments:', error));
    },

    clearForm() {
      this.employee = {
        name: '',
        note: '',
        professionId: null,
        departmentId: null
      };
    }
  }
}
</script>